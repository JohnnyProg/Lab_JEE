package gryta.jan.Lab8.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;

    @JoinColumn(name="address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


    @Lob
    private byte[] attachment;

}
