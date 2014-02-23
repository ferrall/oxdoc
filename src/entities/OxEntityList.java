/**

 oxdoc (c) Copyright 2005-2012 by Y. Zwols

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

package oxdoc.entities;

import oxdoc.AlphanumComparator;

import java.util.*;

public class OxEntityList {
  private static final AlphanumComparator alphanumComparator = new AlphanumComparator();
  private final Hashtable entities = new Hashtable();

  public OxEntity add(OxEntity entity) {
    return add(entity.getName(), entity);
  }

  public OxEntity add(String name, OxEntity entity) {
    entities.put(name, entity);
    return entity;
  }

  public void addAll(OxEntityList list) {
    Collection c = list.entities.values();
    for (Object entity : c)
      add((OxEntity) entity);
  }

  public OxEntity get(String name) {
    return (OxEntity) entities.get(name);
  }

  public int size() {
    return entities.size();
  }

  public ArrayList sortedList() {
    ArrayList list = new ArrayList();
    for (Enumeration e = entities.elements(); e.hasMoreElements(); ) {
      OxEntity entity = (OxEntity) e.nextElement();
      list.add(entity);
    }

    Collections.sort(list, new Comparator() {
      public int compare(Object o1, Object o2) {
        OxEntity e1 = (OxEntity) o1;
        OxEntity e2 = (OxEntity) o2;
        String key1 = e1.getSortKey().toUpperCase();
        String key2 = e2.getSortKey().toUpperCase();
        return alphanumComparator.compare(key1, key2);
      }
    });

    return list;
  }

  public ArrayList getSortedListByDisplayName() {
    return sortedList();
  }

  public OxEntityList getClasses() {
    OxEntityList list = new OxEntityList();
    Set set = entities.entrySet();

    Iterator it = set.iterator();
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry) it.next();
      OxEntity entity = (OxEntity) entry.getValue();
      String name = (String) entry.getKey();
      if (entity instanceof OxClass)
        list.add(name, entity);
    }

    return list;
  }

}
