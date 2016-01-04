package com.himeetu.model;

public class SelectData {
        private int name;
        private int textSize;
        private int textColor;

        public SelectData() {
        }

        public SelectData(int name) {
            this.name = name;
        }

        public SelectData(int name, int textSize, int textColor) {
            this.name = name;
            this.textSize = textSize;
            this.textColor = textColor;
        }

        public void setName(int name) {
            this.name = name;
        }

        public void setTextSize(int textSize) {
            this.textSize = textSize;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public int getName() {
            return name;
        }

        public int getTextSize() {
            return textSize;
        }

        public int getTextColor() {
            return textColor;
        }
    }
