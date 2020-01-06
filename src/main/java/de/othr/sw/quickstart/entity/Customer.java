package de.othr.sw.quickstart.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cID;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Address mainResidence;
    //@ElementCollection
    @OneToMany(mappedBy = "accountHolder")
    private List<Account> accounts;

    //for security
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CustomerRight> customerRights = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorites = new HashSet<>();
        for(CustomerRight customerRight : this.customerRights) {
            authorites.add(new Authority(customerRight.getRight().getRightName()));
        }
        return authorites;
    }

    //getter setter
    public void setUsername(String username) {
        this.username = username;
    }

    public Set<CustomerRight> getCustomerRights() {
        return customerRights;
    }

    public void setCustomerRights(Set<CustomerRight> customerRights) {
        this.customerRights = customerRights;
    }

    public long getcID() {
        return cID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getMainResidence() {
        return mainResidence;
    }

    public void setMainResidence(Address mainResidence) {
        this.mainResidence = mainResidence;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(this.accounts);
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //hardcode, change if needed
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
