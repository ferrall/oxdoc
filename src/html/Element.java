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

package oxdoc.html;

import oxdoc.OxDoc;

public class Element {

	protected OxDoc oxdoc;

	public Element(OxDoc oxdoc) {
		this.oxdoc = oxdoc;
	}

	protected void render(StringBuffer buffer) {
	}

	protected String classAttr(String className) {
		if ((className == null) || (className.trim().length() == 0))
			return "";
		else
			return String.format(" class=\"%s\"", className);
	}

	public String toString() {
		StringBuffer bf = new StringBuffer();
		render(bf);
		return bf.toString();
	}

}