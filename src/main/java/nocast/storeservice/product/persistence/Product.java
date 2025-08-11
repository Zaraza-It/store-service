package nocast.storeservice.product.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import nocast.storeservice.common.components.PriceType;
import nocast.storeservice.common.components.Currency;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
@Entity
@Table(name = "product")
//@Document(indexName = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
//    @Field(type = FieldType.Text)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
//    @Field(type = FieldType.Text)
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
//    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_type", length = 31)
//    @Field(type = FieldType.Text)
    private PriceType priceType;

    @Column(name = "stock_quantity", nullable = false)
//    @Field(type = FieldType.Integer)
    private int stockQuantity;

    @Column(name = "category_id", nullable = false)
//    @Field(type = FieldType.Keyword)
    private Integer categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false, length = 15)
//    @Field(type = FieldType.Text)
    private Currency currency;

    @Column(name = "image", nullable = false, length = 511)
//    @Field(type = FieldType.Text)
    private String image;

    @Column(name = "sort_order", nullable = false)
    @Builder.Default
//    @Field(type = FieldType.Integer)
    private Integer sortOrder = 0;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
//    @Field(type = FieldType.Date)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
//    @Field(type = FieldType.Date)
    private LocalDateTime updatedAt;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
//    @Field(type = FieldType.Boolean)
    private boolean active = true;
}
