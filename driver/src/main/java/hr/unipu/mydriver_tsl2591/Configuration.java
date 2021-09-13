package hr.unipu.mydriver_tsl2591;

class Configuration {
    public float getRez() {
        return lux;
    }

    public void setRez(float lux) {
        this.lux = lux;
    }

    private float lux;
    public Configuration(){}
    public Configuration(Float lux){
        this.lux = lux;
    }


}