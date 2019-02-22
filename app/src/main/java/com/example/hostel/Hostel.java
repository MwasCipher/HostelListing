package com.example.hostel;

class Hostel {

    private String id,hostelName, hostelDescription, hostelCapacity;


    public String getId() {
        return id;
    }

    public Hostel() {

    }

    public Hostel(String id, String hostelName, String hostelDescription, String hostelCapacity) {
        this.id = id;
        this.hostelName = hostelName;
        this.hostelDescription = hostelDescription;
        this.hostelCapacity = hostelCapacity;
    }

    public String getHostelName() {
        return hostelName;
    }

    public String getHostelDescription() {
        return hostelDescription;
    }

    public String getHostelCapacity() {
        return hostelCapacity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public void setHostelDescription(String hostelDescription) {
        this.hostelDescription = hostelDescription;
    }

    public void setHostelCapacity(String hostelCapacity) {
        this.hostelCapacity = hostelCapacity;
    }
}
