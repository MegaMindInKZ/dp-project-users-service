package com.example.users.exceptions.caution;

public class CautionableBehaviour implements CautionBehaviour{
    private Object caution;
    public CautionableBehaviour(Object caution){
        this.caution = caution;
    }

    @Override
    public Object getCaution() {
        return caution;
    }
}
