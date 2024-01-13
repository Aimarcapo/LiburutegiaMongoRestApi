package liburutegi.demo.model;
//Hau properties klasea da honek liburutegi bakoitzean dagoen beste atal bat da
public class Properties {
    private String actividad_tipo;
    private String nombre;
    private String  tipo_via_codigo;
    private String tipo_via_descripcion;
    private String direccion_nombre_via;
    private String direccion_numero;
    private int direccion_codigo_postal; 
    private int municipio_codigo;
    private String municipio_nombre;
    private String referencia;
    private String web;
    private String email;
    private int telefono;
    private long fax;
    private double longitud;
private double latitud;
    
    private String fecha_creacion;
    private String fecha_actualizacion;
    public Properties(String nombre) {
    this.nombre = nombre;
}

 

    public Properties(String actividad_tipo, String nombre, String tipo_via_codigo, String tipo_via_descripcion,
            String direccion_nombre_via, String direccion_numero, int direccion_codigo_postal, int municipio_codigo,
            String municipio_nombre, String referencia, String web, String email, int telefono, long fax,
            double longitud, double latitud, String fecha_creacion, String fecha_actualizacion) {
        this.actividad_tipo = actividad_tipo;
        this.nombre = nombre;
        this.tipo_via_codigo = tipo_via_codigo;
        this.tipo_via_descripcion = tipo_via_descripcion;
        this.direccion_nombre_via = direccion_nombre_via;
        this.direccion_numero = direccion_numero;
        this.direccion_codigo_postal = direccion_codigo_postal;
        this.municipio_codigo = municipio_codigo;
        this.municipio_nombre = municipio_nombre;
        this.referencia = referencia;
        this.web = web;
        this.email = email;
        this.telefono = telefono;
        this.fax = fax;
        this.longitud = longitud;
        this.latitud = latitud;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
    }



    public String getFecha_creacion() {
        return fecha_creacion;
    }



    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }



    public String getFecha_actualizacion() {
        return fecha_actualizacion;
    }



    public void setFecha_actualizacion(String fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }



    public Properties(){}
   
    public String getActividad_tipo() {
        return actividad_tipo;
    }
    public void setActividad_tipo(String actividad_tipo) {
        this.actividad_tipo = actividad_tipo;
    }
    public String getTipo_via_codigo() {
        return tipo_via_codigo;
    }
    public void setTipo_via_codigo(String tipo_via_codigo) {
        this.tipo_via_codigo = tipo_via_codigo;
    }
    public String getTipo_via_descripcion() {
        return tipo_via_descripcion;
    }
    public void setTipo_via_descripcion(String tipo_via_descripcion) {
        this.tipo_via_descripcion = tipo_via_descripcion;
    }
    public String getDireccion_nombre_via() {
        return direccion_nombre_via;
    }
    public void setDireccion_nombre_via(String direccion_nombre_via) {
        this.direccion_nombre_via = direccion_nombre_via;
    }
    public String getDireccion_numero() {
        return direccion_numero;
    }
    public void setDireccion_numero(String direccion_numero) {
        this.direccion_numero = direccion_numero;
    }
    public int getDireccion_codigo_postal() {
        return direccion_codigo_postal;
    }
    public void setDireccion_codigo_postal(int direccion_codigo_postal) {
        this.direccion_codigo_postal = direccion_codigo_postal;
    }
    public int getMunicipio_codigo() {
        return municipio_codigo;
    }
    public void setMunicipio_codigo(int municipio_codigo) {
        this.municipio_codigo = municipio_codigo;
    }
    public String getMunicipio_nombre() {
        return municipio_nombre;
    }
    public void setMunicipio_nombre(String municipio_nombre) {
        this.municipio_nombre = municipio_nombre;
    }
    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public String getWeb() {
        return web;
    }
    public void setWeb(String web) {
        this.web = web;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public long getFax() {
        return fax;
    }
    public void setFax(long fax) {
        this.fax = fax;
    }
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Properties [nombre=" + nombre + "]";
    }
    
    
}
