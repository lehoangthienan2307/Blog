package com.lhtan.blogapplication.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "authority")
@Getter
@Setter
@NoArgsConstructor
public class AuthorityEntity implements Serializable {
	
	@Id
	@Column(length = 16)
	private String name;
	
	@Override
	public String toString() {
		return "Authority{" + "name='" + name + "'" + "}";
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthorityEntity authority1 = (AuthorityEntity) o;

        return name.equals(authority1.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
