package org.cred;

import jakarta.persistence.Embeddable;

@Embeddable

public class StudentName {
private String FirstName;
private String MiddleName;
private String LastName;
public String getFirstName() {
	return FirstName;
}
public void setFirstName(String firstName) {
	FirstName = firstName;
}
public String getMiddleName() {
	return MiddleName;
}
public void setMiddleName(String middleName) {
	MiddleName = middleName;
}
public String getLastName() {
	return LastName;
}
public void setLastName(String lastName) {
	LastName = lastName;
}

}
