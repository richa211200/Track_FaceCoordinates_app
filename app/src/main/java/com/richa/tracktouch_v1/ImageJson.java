package com.richa.tracktouch_v1;

public class ImageJson {
    //public String imagePath;
    public float coordinateX;
    public float coordinateY;

    public ImageJson(float coordinateX, float coordinateY) {
      //  this.imagePath = imagePath;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

//    public String getImagePath() {
//        return imagePath;
//    }
//
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }


    @Override
    public String toString() {
        return "ImageData{" +
              //  "Imagepath='" + imagePath + '\'' +
                ", X coordinate=" + coordinateX +
                ", Y coordinate=" + coordinateY +
                '}';
    }
}
