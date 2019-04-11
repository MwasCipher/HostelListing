package com.example.hostel;

class Hostel  {

    private String id, hostelOwnerId,hostelName, hostelDescription, hostelCapacity, hostelPrice;

    public Hostel() {

    }

    public Hostel(String hostelOwnerId,String id,  String hostelName, String hostelDescription,
                  String hostelCapacity,  String hostelPrice) {

        this.hostelOwnerId = hostelOwnerId;
        this.id = id;
        this.hostelName = hostelName;
        this.hostelDescription = hostelDescription;
        this.hostelCapacity = hostelCapacity;
        this.hostelPrice = hostelPrice;
    }



    public String getHostelOwnerId() {
        return hostelOwnerId;
    }

    public void setHostelOwnerId(String hostelOwnerId) {
        this.hostelOwnerId = hostelOwnerId;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getHostelDescription() {
        return hostelDescription;
    }

    public void setHostelDescription(String hostelDescription) {
        this.hostelDescription = hostelDescription;
    }

    public String getHostelCapacity() {
        return hostelCapacity;
    }

    public void setHostelCapacity(String hostelCapacity) {
        this.hostelCapacity = hostelCapacity;
    }

    public String getHostelPrice() {
        return hostelPrice;
    }

    public void setHostelPrice(String hostelPrice) {
        this.hostelPrice = hostelPrice;
    }
}
