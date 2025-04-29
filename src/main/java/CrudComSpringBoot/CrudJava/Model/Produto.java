package CrudComSpringBoot.CrudJava.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;


@Entity(name = "product")
@Table(name = "product")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String  name;
    private Long price;

    public Produto() {
    }

    public Produto(int id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
