package ge.generator.jdbc.api;

import java.sql.*;
import java.util.*;

import ge.generator.jdbc.api.links.*;
import ge.generator.jdbc.impl.*;
import org.apache.log4j.*;

public abstract class SchemaLoader
{
    private static final Logger logger = Logger.getLogger(SchemaLoader.class);

    public Schema loadSchema( Connection connection ) throws
                                                      SQLException
    {
        logger.info("Loading Schema Data.");
        SchemaData schemaData = loadSchemaData( connection );

        logger.info("Filtering Schema Data.");
        schemaData = filterSchemaData( schemaData );

        logger.info("Processing Schema Data.");
        return processSchemaData( schemaData );
    }

    private Schema processSchemaData( SchemaData schemaData )
    {
        Schema retVal = new Schema();

        List<TableData> tableDataList = schemaData.getTables();

        for ( TableData tableData : tableDataList )
        {
            if ( tableData.isLinkTable() == false )
            {
                String tableName = tableData.getTableName();

                if ( tableData.isCompositeTable() == false )
                {
                    logger.info("Creating Primary Key Table: "+ tableName );
                    PrimaryKeyTable table = new PrimaryKeyTable( tableName );

                    retVal.addTable( table );
                }
                else
                {
                    logger.info("Creating Composite Key Table: "+ tableName );
                    CompositeKeyTable table = new CompositeKeyTable( tableName );

                    retVal.addTable( table );
                }
            }
        }

        for ( TableData tableData : tableDataList )
        {
            String tableName = tableData.getTableName();

            Table table = retVal.getTable( tableName );

            List<ColumnData> columnDataList = tableData.getColumnData();

            for ( ColumnData columnData : columnDataList )
            {
                String columnName = columnData.getColumnName();

                List<LinkData> links = tableData.getLinks( columnName );

                if ( tableData.isColumnKey( columnName ) == true )
                {
                    if ( table instanceof CompositeKeyTable )
                    {
                        CompositeKeyTable compositeKeyTable = ( CompositeKeyTable ) table;

                        CompositeKeyColumn compositeKeyColumn = new CompositeKeyColumn( columnName );
                        compositeKeyColumn.setIndex( columnData.getColumnIndex() );
                        compositeKeyColumn.setType( columnData.getDataType() );

                        compositeKeyTable.addKeyColumn( compositeKeyColumn );

                        if ( links.size() != 0 )
                        {
                            for ( LinkData linkData : links )
                            {
                                if ( ( tableName.equals( linkData.getTableName1() ) == true ) &&
                                     ( columnName.equals( linkData.getColumnName1() ) == true ) )
                                {
                                    TableData linkTable = schemaData.getTableData( linkData.getTableName2() );
                                    ColumnData linkColumn = linkTable.getColumnData( linkData.getColumnName2() );

                                    if ( linkTable.isColumnKey( linkColumn.getColumnName() ) == true )
                                    {
                                        if ( ( linkTable.isLinkTable() == false ) &&
                                             ( linkTable.isCompositeTable() == false ) )
                                        {
                                            ManyToOneLink manyToOneLink = new ManyToOneLink();

                                            manyToOneLink.setColumnName( columnName );
                                            manyToOneLink.setLinkTableName( linkData.getTableName2() );

                                            table.addLink( manyToOneLink );
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if ( table instanceof PrimaryKeyTable )
                    {
                        PrimaryKeyTable primaryKeyTable = ( PrimaryKeyTable ) table;

                        PrimaryKeyColumn primaryKeyColumn = new PrimaryKeyColumn( columnName );
                        primaryKeyColumn.setIndex( columnData.getColumnIndex() );
                        primaryKeyColumn.setType( columnData.getDataType() );

                        primaryKeyTable.setPrimaryKeyColumn( primaryKeyColumn );

                        if ( links.size() != 0 )
                        {
                            for ( LinkData link : links )
                            {
                                if ( ( tableName.equals( link.getTableName2() ) == true ) &&
                                     ( columnName.equals( link.getColumnName2() ) == true ) )
                                {
                                    TableData linkTable = schemaData.getTableData( link.getTableName1() );
                                    ColumnData linkColumn = linkTable.getColumnData( link.getColumnName1() );

                                    if ( linkTable.isColumnKey( linkColumn.getColumnName() ) == true )
                                    {
                                        if ( linkTable.isLinkTable() == true )
                                        {
                                            ManyToManyLink manyToManyLink = new ManyToManyLink();

                                            List<LinkData> linkTableLinks = linkTable.getLinks();

                                            LinkData otherLink = null;

                                            for ( LinkData linkTableLink : linkTableLinks )
                                            {
                                                if ( ( linkTableLink != link ) &&
                                                     ( linkTable.isColumnKey( linkTableLink.getColumnName1() ) ==
                                                       true ) )
                                                {
                                                    otherLink = linkTableLink;
                                                }
                                            }

                                            manyToManyLink.setJoinTableName( linkTable.getTableName() );
                                            manyToManyLink.setJoinColumnName(  linkColumn.getColumnName());
                                            manyToManyLink.setJoinColumnReferencedName( columnName );
                                            manyToManyLink.setInverseJoinColumnName( otherLink.getColumnName1() );
                                            manyToManyLink.setInverseJoinColumnReferencedName( otherLink.getColumnName2() );
                                            manyToManyLink.setDestinationTableName( otherLink.getTableName2() );

                                            table.addLink( manyToManyLink );
                                        }
                                        else if ( linkTable.isCompositeTable() == true )
                                        {
                                            OneToManyLink oneToManyLink = new OneToManyLink();

                                            oneToManyLink.setLinkTableName( linkTable.getTableName() );
                                            oneToManyLink.setLinkColumnName( linkColumn.getColumnName() );

                                            table.addLink( oneToManyLink );
                                        }
                                        else
                                        {
                                            PrimaryKeyJoinOneToOneLink primaryKeyJoinOneToOneLink =
                                                    new PrimaryKeyJoinOneToOneLink();

                                            primaryKeyJoinOneToOneLink.setLinkTableName( linkTable.getTableName() );

                                            table.addLink( primaryKeyJoinOneToOneLink );
                                        }
                                    }
                                    else
                                    {
                                        OneToManyLink oneToManyLink = new OneToManyLink();

                                        oneToManyLink.setLinkTableName( linkTable.getTableName() );
                                        oneToManyLink.setLinkColumnName( linkColumn.getColumnName() );

                                        table.addLink( oneToManyLink );
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    if ( links.size() == 1 )
                    {
                        LinkData linkData = links.get( 0 );

                        if ( ( tableName.equals( linkData.getTableName1() ) == true ) &&
                             ( columnName.equals( linkData.getColumnName1() ) == true ) )
                        {
                            ManyToOneLink manyToOneLink = new ManyToOneLink();

                            manyToOneLink.setColumnName( columnName );
                            manyToOneLink.setLinkTableName( linkData.getTableName2() );

                            table.addLink( manyToOneLink );
                        }
                    }
                    else
                    {
                        Column column = new Column( columnData.getColumnName() );
                        column.setIndex( columnData.getColumnIndex() );
                        column.setType( columnData.getDataType() );
                        column.setDataScale(columnData.getDataScale());

                        table.addColumn( column );
                    }
                }
            }
        }

        return retVal;
    }

    protected SchemaData filterSchemaData( SchemaData schemaData )
    {
        return schemaData;
    }

    private SchemaData loadSchemaData( Connection connection ) throws
                                                               SQLException
    {
        SchemaData retVal = new SchemaData();

        List<String> tableNames = getTableNames( connection );

        for ( String tableName : tableNames )
        {
            retVal.addTable( tableName );
        }

        List<ColumnData> columnData = getColumnData( connection, tableNames );

        for ( ColumnData data : columnData )
        {
            retVal.addColumnData( data );
        }

        List<KeyData> keyData = getKeyData( connection, tableNames );

        for ( KeyData data : keyData )
        {
            retVal.addKeyData( data );
        }

        List<LinkData> linkData = getLinkData( connection, tableNames );

        for ( LinkData data : linkData )
        {
            retVal.addLinkData( data );
        }

        return retVal;
    }

    protected abstract List<String> getTableNames( Connection connection ) throws
                                                                           SQLException;

    protected abstract List<ColumnData> getColumnData( Connection connection, List<String> tableNames ) throws
                                                                                                        SQLException;

    protected abstract List<KeyData> getKeyData( Connection connection, List<String> tableNames ) throws
                                                                                                  SQLException;

    protected abstract List<LinkData> getLinkData( Connection connection, List<String> tableNames ) throws
                                                                                                    SQLException;
}
