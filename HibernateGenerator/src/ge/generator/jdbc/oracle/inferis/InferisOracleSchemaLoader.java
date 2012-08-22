package ge.generator.jdbc.oracle.inferis;

import java.util.*;

import ge.generator.jdbc.impl.*;
import ge.generator.jdbc.oracle.*;

public class InferisOracleSchemaLoader extends OracleSchemaLoader
{
    @Override
    protected SchemaData filterSchemaData( SchemaData schemaData )
    {
        List<ColumnData> columnData = schemaData.getAllColumnData();
        List<ColumnData> remove = new ArrayList<ColumnData>();

        for ( ColumnData data : columnData )
        {
            String columnName = data.getColumnName();

            if ( columnName.matches("NEW_[\\S]*_ID") == true )
            {
                System.out.println(columnName);
            }

            if (( columnName.startsWith("NEW_") == true ) && ( columnName.endsWith("_ID") == true ))
            {
                remove.add(data);
            }
        }

        for ( ColumnData data : remove )
        {
            schemaData.removeColumnData( data );
        }

        return schemaData;
    }
}
