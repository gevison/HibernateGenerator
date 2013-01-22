package ge.generator.jdbc.oracle.inferis;

import java.util.*;

import ge.generator.jdbc.impl.*;
import ge.generator.jdbc.oracle.*;

public class InferisOracleSchemaLoader extends OracleSchemaLoader
{
    @Override
    protected SchemaData filterSchemaData( SchemaData schemaData )
    {
        List<TableData> tableData = schemaData.getTables();
        List<TableData> removeTable = new ArrayList<TableData>();
        List<ColumnData> removeColumn = new ArrayList<ColumnData>();

        for ( TableData data : tableData )
        {
            String tableName = data.getTableName();

            if ( tableName.startsWith("MIG_") == true )
            {
                removeTable.add(data);
            }
            else if ( tableName.equals("GIS_CLAIM") == true )
            {
                removeTable.add(data);
            }
            else if ( tableName.equals("TX_CLAIM_ITEM") == true )
            {
                removeTable.add(data);
            }
            else if ( tableName.equals("INSPECTION_TYPES") == false )
            {
                ColumnData inspection_type = data.getColumnData( "INSPECTION_TYPE" );

                if ( inspection_type != null )
                {
                    removeColumn.add(inspection_type);
                }

                List<ColumnData> columnData = data.getColumnData();

                for ( ColumnData cData : columnData )
                {
                    String columnName = cData.getColumnName();

                    if (( columnName.startsWith("NEW_") == true ) && ( columnName.endsWith("_ID") == true ))
                    {
                        removeColumn.add( cData );
                    }
                }
            }
        }

        for ( TableData data : removeTable )
        {
            schemaData.removeTableData(data);
        }

        for ( ColumnData data : removeColumn )
        {
            schemaData.removeColumnData( data );
        }

        return schemaData;
    }
}
