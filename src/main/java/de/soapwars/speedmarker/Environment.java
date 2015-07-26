package de.soapwars.speedmarker;

/**
 * Created by ptriller on 04.07.2015.
 */
public interface Environment {

   Environment fetchNamespace(String name);

   void addMacro(String name, Node node);

   void addFunction(String name, Node node);

   void setGlobalValue(String name, Object value);

   Object getValue(String name, Object value);

   Object setLocalValue(String name, Object value);

   Environment newScope();


}
