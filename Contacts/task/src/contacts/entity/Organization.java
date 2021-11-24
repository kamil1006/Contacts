package contacts.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Organization extends Contact implements Serializable {

   private String address;


   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }


}
