package com.kilometre.zero.location;

public record Location(String city, String country) {

    public static final Location UNKNOWN = new Location(null, null);
    
    public boolean isResolved() {
        return city != null || country != null;
    }

}
