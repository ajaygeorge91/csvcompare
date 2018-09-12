package org.jboss.windup.utils.model;

import java.util.ArrayList;
import java.util.List;

public class StringArrayModel {
    private String[] array;

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public StringArrayModel(String[] array) {
        this.array = array;
    }

    public static List<StringArrayModel> fromStringArrayList(List<String[]> stringArrayList){
        List<StringArrayModel> stringArrayModelList = new ArrayList<>();
        for (String[] sArray : stringArrayList){
            stringArrayModelList.add(new StringArrayModel(sArray));
        }
        return stringArrayModelList;
    }

    public static List<String[]> fromStringArrayModelList(List<StringArrayModel> stringArrayModelList){
        List<String[]> stringArrayList = new ArrayList<>();
        for (StringArrayModel sArrayModel : stringArrayModelList){
            stringArrayList.add(sArrayModel.array);
        }
        return stringArrayList;
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        for (String value : array)
        {
            if (value != null)
                hashcode += value.hashCode();
        }
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        String[] s1 = this.array;

        if (getClass() != obj.getClass())
            return false;
        StringArrayModel other = (StringArrayModel) obj;

        String[] s2 = other.array;
        if (s1 == s2)
            return true;

        if (s1 == null || s2 == null)
            return false;

        int n = s1.length;
        if (n != s2.length)
            return false;

        for (int i = 0; i < n; i++) {
            if (!s1[i].equals(s2[i]))
                return false;
        }

        return true;
    }

    private String emptyStringIfNull(Object obj) {

        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
