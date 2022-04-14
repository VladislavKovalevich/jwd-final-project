package by.vlad.JavaWebProject.entity;

public class Publisher extends AbstractEntity{
    private String name;

    public Publisher() {
    }

    public Publisher(long id) {
        super(id);
    }

    public Publisher(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher that = (Publisher) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BookPublisher{" +
                "name='" + name + '\'' +
                '}';
    }
}
