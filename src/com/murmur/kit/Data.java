package com.murmur.kit;

public class Data<T> {
    private T data;
    private int length;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                ", length=" + length +
                '}';
    }
}
