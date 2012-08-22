package ge.generator.java.generated;

public class ParentObject
{
    private ParentObject parent;

    public void setParent( ParentObject parent )
    {
        this.parent = parent;
    }

    public ParentObject getParent()
    {
        return parent;
    }

    public String getPackageName()
    {
        if ( parent == null )
        {
            return null;
        }
        else
        {
            return parent.getPackageName();
        }
    }
}
