import org.sql2o.Connection;

import java.util.List;

public class Animal {

    public String location;
    public String name;
    private int id;

    public Animal(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }

    }

    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id= :id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object anotherAnimal) {
        if (!(anotherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) anotherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getLocation().equals(newAnimal.getLocation());
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, location) VALUES (:name, :location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("location", this.location)
                    .executeUpdate()
                    .getKey();
        }
    }

    public int getId() {
        return id;
    }
}
