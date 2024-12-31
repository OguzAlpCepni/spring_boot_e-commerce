package ecommerce.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "product_id")
    private String productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price", precision = 19, scale = 4)
    private BigDecimal price;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_in_stock")
    private int unitInStock;
    @Column(name = "sku")
    private String sku;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<BasketItem> basketItems = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<OrderItem> OrderItem = new ArrayList<>();
    @ManyToMany()
    @JoinTable(
            name="product_category",// ortak tablo adi
            joinColumns = @JoinColumn(name = "product_id"),// Product ile ilişki
            inverseJoinColumns = @JoinColumn(name = "category_id")// Category ile ilişki
    )
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();
}
