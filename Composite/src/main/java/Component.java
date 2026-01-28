public abstract class Component {
    protected String name;
    public Component(String name) {
        this.name = name;
    }
    public void display(){
        System.out.println("<name>"+name+"</name>");
    };
    public abstract void add(Component component);
    public abstract void remove(Component component);
    public abstract Component getChild(int index);
}
