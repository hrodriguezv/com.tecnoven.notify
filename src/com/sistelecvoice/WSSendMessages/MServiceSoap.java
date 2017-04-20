/**
 * MServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sistelecvoice.WSSendMessages;

public interface MServiceSoap extends java.rmi.Remote {
    public com.sistelecvoice.WSSendMessages.Respuesta enviarMensaje(java.lang.String usuario, java.lang.String password, java.lang.String telefono, java.lang.String mensaje) throws java.rmi.RemoteException;
    public com.sistelecvoice.WSSendMessages.Cupos consultarCupos(java.lang.String usuario, java.lang.String password) throws java.rmi.RemoteException;
    public com.sistelecvoice.WSSendMessages.Respuesta enviarMensajesVarios(java.lang.String usuario, java.lang.String password, java.lang.String telefonos, java.lang.String mensaje) throws java.rmi.RemoteException;
}
