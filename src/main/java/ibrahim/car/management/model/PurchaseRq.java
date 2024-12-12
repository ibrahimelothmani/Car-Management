package ibrahim.car.management.model;


import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PurchaseRq {
    private  Integer idClient;
    private  Integer idCar;
    @Column(columnDefinition = "LONGTEXT")
    @Lob
    private String card;
}
