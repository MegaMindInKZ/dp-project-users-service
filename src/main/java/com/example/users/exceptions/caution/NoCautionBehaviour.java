package com.example.users.exceptions.caution;

public class NoCautionBehaviour implements CautionBehaviour{
    @Override
    public Object getCaution() {
        return null;
    }
}
