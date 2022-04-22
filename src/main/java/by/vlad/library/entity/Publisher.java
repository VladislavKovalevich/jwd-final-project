package by.vlad.library.entity;

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

        Publisher publisher = (Publisher) o;

        return name.equals(publisher.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("BookPublisher{" + "name='").append(name).append('\'')
                .append('}')
                .toString();
    }
}
