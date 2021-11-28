package in.anupbaranwal.mutation.model;

public class User {

  private Integer uanNumber;
  private String name;
  private String contactNo;

  public User() {}

  public User(Integer uanNumber, String name, String contactNo) {
    this.uanNumber = uanNumber;
    this.name = name;
    this.contactNo = contactNo;
  }

  public Integer getUanNumber() {
    return uanNumber;
  }

  public void setUanNumber(Integer uanNumber) {
    this.uanNumber = uanNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContactNo() {
    return contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }
}
