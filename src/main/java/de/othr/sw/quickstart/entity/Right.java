package de.othr.sw.quickstart.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Right {
    @Id
    private Integer rightID;
    private String rightName;
    @OneToMany(mappedBy = "right", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    private Set<CustomerRight> customerRights = new HashSet<>();

    public Integer getRightID() {
        return rightID;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public Set<CustomerRight> getCustomerRights() {
        return customerRights;
    }

    public void setCustomerRights(Set<CustomerRight> customerRights) {
        this.customerRights = customerRights;
    }
}
