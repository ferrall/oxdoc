/**

oxdoc (c) Copyright 2005-2010 by Y. Zwols

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

**/
import java.util.*;


public class OxClass extends OxEntity {

   public enum Visibility { 
	Private { public String toString() { return "private"; } },
	Protected { public String toString() { return "protected"; } },
	Public { public String toString() { return "public"; } }
   };

   private interface MemberFilter {
      boolean keepItem(OxEntity entity);
   };

   public String Declaration;
   private OxEntityList _members = new OxEntityList();
   private OxFile _parentFile = null;
   private String _superClassName = null;

   OxClass(String name, OxFile parentFile) {
      super(name, new ClassComment(parentFile.project()), parentFile);
      _parentFile = parentFile;
   }

   OxClass(String name, String superClassName, OxFile parentFile) {
      super(name, new ClassComment(parentFile.project()), parentFile);
      _parentFile = parentFile;
      setIconType(FileManager.CLASS);
      _superClassName = superClassName;
   }

   public OxMethod addMethod(String name) {
      return (OxMethod) _members.add(new OxMethod(name, this));
   }

   public OxField addField(String name, Visibility vis) {
      return (OxField) _members.add(new OxField(name, this, vis));
   }

   public ArrayList members() {
      return _members.sortedList();
   }

   public ArrayList filterMembers(MemberFilter filter) {
	  ArrayList members = members();
	  ArrayList list = new ArrayList();

      for (int i = 0; i < members.size(); i++) {
         OxEntity entity = (OxEntity) members.get(i);
         if (filter.keepItem(entity))
            list.add(entity);
      }
      return list;
   }

   public ArrayList getPrivateFields() {

	  return filterMembers( new MemberFilter() { 
            public boolean keepItem(OxEntity entity) 
            {
                return ((entity instanceof OxField) &&  (((OxField) entity).visibility() == Visibility.Private ));
            }
      });
   }

   public ArrayList getProtectedFields() {

	  return filterMembers( new MemberFilter() { 
            public boolean keepItem(OxEntity entity) 
            {
                return ((entity instanceof OxField) &&  (((OxField) entity).visibility() == Visibility.Protected ));
            }
      });
   }

   public ArrayList getPublicFields() {

	  return filterMembers( new MemberFilter() { 
            public boolean keepItem(OxEntity entity) 
            {
                return ((entity instanceof OxField) &&  (((OxField) entity).visibility() == Visibility.Public ));
            }
      });
   }

   public ArrayList getMethods() {

	  return filterMembers( new MemberFilter() { 
            public boolean keepItem(OxEntity entity) 
            {
                return (entity instanceof OxMethod);
            }
      });
   }

   public OxMethod methodByName(String s) {
      return (OxMethod) _members.get(name() + "::" + s);
   }

   public OxField fieldByName(String s) {
      return (OxField) _members.get(name() + "::" + s);
   }

   public OxFile parentFile() {
      return _parentFile;
   }

   public String superClassName() {
      return _superClassName;
   }

   public OxClass superClass() {
      if (_superClassName == null)
         return null;

      return (OxClass) parentFile().project().getSymbol(_superClassName);
   }

   public String url() {
      return parentFile().url();
   }

   public String toString() {
      return "<OxClass " + name() + ">";
   }
}
