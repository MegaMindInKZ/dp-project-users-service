package com.example.users.utils.exceptions.caution;

public class NoCautionBehaviour implements CautionBehaviour{
    @Override
    public Object getCaution() {
        return null;
    }
}
