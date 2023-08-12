package lk.ijse.gdse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer  implements SuperEntity{
    @Id
    String id;
    String name;
    String address;
    Double salary;

    @OneToMany(targetEntity = Orders.class, mappedBy = "customer")
    @ToString.Exclude
    List<Orders> ordersList = new ArrayList<>();
}
