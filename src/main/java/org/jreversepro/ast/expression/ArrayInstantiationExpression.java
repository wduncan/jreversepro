/**
 *  @(#) ArrayInstantiationExpression.java
 *
 * JReversePro - Java Decompiler / Disassembler.
 * Copyright (C) 2008 Karthik Kumar.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *  
 *  	http://www.apache.org/licenses/LICENSE-2.0 
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 **/
package org.jreversepro.ast.expression;

import java.util.ArrayList;
import java.util.List;

import org.jreversepro.jls.JLSConstants;
import org.jreversepro.jvm.TypeInferrer;
import org.jreversepro.reflect.Import;


/**
 * @author akkumar
 * 
 */
public class ArrayInstantiationExpression extends Expression {

  public ArrayInstantiationExpression(char _arrayMemberType,
      Expression _arraySize) {
    this(String.valueOf(_arrayMemberType), _arraySize);
  }

  public ArrayInstantiationExpression(String _arrayMemberType,
      Expression _arraySize) {
    super(String.valueOf(JVM_TYPE_ARRAY) + _arrayMemberType, L_REF);
    arraySizes = new ArrayList<Expression>();
    arraySizes.add(_arraySize);
    arrayMemberType = _arrayMemberType;
  }

  public ArrayInstantiationExpression(String _arrayMemberType,
      List<Expression> _arraySizes) {
    super(String.valueOf(JVM_TYPE_ARRAY) + _arrayMemberType, L_REF);
    arraySizes = _arraySizes;
    arrayMemberType = _arrayMemberType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.jrevpro.jls.expression.Expression#getJLSRepresentation()
   */
  @Override
  public String getJLSCode() {
    String jlstype = Import.getClassName(TypeInferrer.getJLSType(
        arrayMemberType, false));
    // Get Class Name

    StringBuilder sb = new StringBuilder();
    sb.append(JLSConstants.NEW + " " + jlstype);

    for (Expression expr : arraySizes) {
      sb.append(JLSConstants.OPEN_SQUARE_BRACKET + expr.getJLSCode()
          + JLSConstants.CLOSE_SQUARE_BRACKET);
    }
    return sb.toString();

  }

  String arrayMemberType;

  List<Expression> arraySizes;

}
