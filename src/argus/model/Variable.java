/*
 * Argus Open Source
 * Software to apply Statistical Disclosure Control techniques
 *
 * Copyright 2014 Statistics Netherlands
 *
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the European Union Public Licence 
 * (EUPL) version 1.1, as published by the European Commission.
 *
 * You can find the text of the EUPL v1.1 on
 * https://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 *
 * This software is distributed on an "AS IS" basis without 
 * warranties or conditions of any kind, either express or implied.
 */
package argus.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
//import tauargus.extern.dataengine.TauArgus;
import argus.utils.TauArgusUtils;
import argus.utils.Tokenizer;

public class Variable implements Cloneable {
    
    private static final Logger logger = Logger.getLogger(Variable.class.getName());
    
    //private TauArgus tauArgus = Application.getTauArgusDll();

    // Determines lengths of fixed sized arrays being used
    public static final int MAX_NUMBER_OF_MISSINGS = 2;
    public static final int MAX_NUMBER_OF_REQUESTS = 2;
    public static final int MAX_NUMBER_OF_DIST = 5;
    public static final int MAX_NUMBER_OF_HIER_LEVELS = 10;

    // Possible values for variable hierarchical;
    public static final int HIER_NONE = 0;
    public static final int HIER_LEVELS = 1;
    public static final int HIER_FILE = 2;
    
    public int index; // index for interfacing with TauArgus dll
    
    public Metadata metadata = null;
    public Variable originalVariable;

    public String name = "";
    public Type type;
    public int bPos = 1; // Only used if data file type is fixed format
    public int varLen; 
    public int nDecimals; // Only used if hasDecimals() returns true

    // Only used by variables of type 'Categorical'
    public String[] missing;
    public String totCode = "";
    public boolean hasDistanceFunction;
    public int[] distanceFunction;
    public String codeListFile = "";
    public int hierarchical;
    public int hierLevelsSum;
    public int[] hierLevels;
    public String hierFileName = "";
    public String leadingString = ".";

    // Only used by variables of type 'Request'
    public String[] requestCode;

    // Only used in case of a recoded variable of type 'Categorical'
    public boolean recoded;
    public String currentRecodeFile = "";
    public String currentRecodeCodeListFile = "";

    public boolean inTable;

    public boolean truncatable;
    public int truncLevels;
    
    public Variable(Metadata metadata) {
        this.metadata = metadata;
        this.originalVariable = this;
    }
    
    public boolean isNumeric() {
        return type.isNumeric();
    }
    
    public boolean hasDecimals() {
        return type.hasDecimals();
    }
    
    public boolean isCategorical() {
        return type.isCategorical();
    }
    
    public boolean isResponse() {
        return type.isResponse();
    }
    
    public boolean isTotalCode(String code) {
        return code.equalsIgnoreCase(totCode);
    }

    public boolean isTotalCode2(String code) {
        return code.trim().equals(totCode) || code.trim().equals("");
    }
    
    public boolean isMissing(String code) {
        for (int m = 0; m < Variable.MAX_NUMBER_OF_MISSINGS; m++) {
            if (StringUtils.isNotEmpty(missing[m]) && code.equals(missing[m]))  {
                return true;
            }
        }
        return false;
    }
    
    public String getTotalCode() {
        if (totCode.equals("")) {
            return "Total";
        } else {
            return totCode;
        }
    }

    public int numberOfMissings() {
        int nMissings = 0;
        if (isCategorical()) {
            while (nMissings < MAX_NUMBER_OF_MISSINGS && StringUtils.isNotEmpty(missing[nMissings])) {
                nMissings++;
            }
        }
        return nMissings;
    }

    public String normaliseMissing(String missing) {
        if (StringUtils.isBlank(missing)) {
            missing = "";
        } else {
            missing = padCode(missing);
        }
        return missing;
    }
    
    public String normaliseCode(String code) {
        if (StringUtils.isNotEmpty(code) && hierarchical == HIER_NONE) {
            code = padCode(code);
        } 
        return code;
    }
    
    public String padCode(String code) {
        return StringUtils.leftPad(code, varLen);
    }
    
//    public RecodeInfo readRecodeFile(String fileName) throws ArgusException, FileNotFoundException, IOException {
//        String recodeData = "";
//        String missing1 = "";
//        String missing2 = "";
//        String codeList = "";
//// Anco 1.6 try with recources        
////        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
//        BufferedReader reader = null;
//        try {reader = new BufferedReader(new FileReader(fileName));
//              Tokenizer tokenizer = new Tokenizer(reader);
//            while ((tokenizer.nextLine()) != null) {
//                String hs;
//                String token = tokenizer.nextToken();
//                hs = tokenizer.getLine();
//                if (token.equals("<MISSING>")) {
//                    missing1 = tokenizer.nextToken();
//                    if (missing1.equals("")) {
//                        throw new ArgusException("No Missing Values found after <MISSING>");
//                    }
//                    token = tokenizer.nextToken();
//                    if (token.equals(",")) {
//                        token = tokenizer.nextToken();
//                    }
//                    missing2 = token;
//                } else if (token.equals("<CODELIST>")) {
//                    codeList = tokenizer.nextToken();
//                } else if (!token.equals("")) {
//                    recodeData = recodeData + hs + "\n";
////                    recodeData = recodeData + token + "\n";
//                }
//            }
//            return new RecodeInfo(recodeData, missing1, missing2, codeList);
//        }
//        finally {reader.close();}
//    }
//
//    public void writeRecodeFile(String fileName, RecodeInfo recodeInfo) throws IOException {
//// anco 1.6 try with resources        
////        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));) {
//       BufferedWriter writer = null; 
//       try {writer = new BufferedWriter(new FileWriter(fileName));  
//            if (!recodeInfo.getMissing1().equals("") || !recodeInfo.getMissing2().equals("")) {
//                writer.write("<MISSING> " + recodeInfo.getMissing1() + " " + recodeInfo.getMissing2() + "\n");
//            }
//            if (!recodeInfo.getCodeList().equals("")) {
//                writer.write("<CODELIST> " + recodeInfo.getCodeList() + "\n");
//            }
//            writer.write(recodeInfo.getRecodeData() + "\n");
//        }
//       finally {writer.close();}
//    }
//
//    public void recode(RecodeInfo recodeInfo) throws ArgusException {
//        int nMissing = 0; String hs;
//        if (StringUtils.isNotBlank(recodeInfo.getMissing1())) {
//            nMissing = 1;
//        }
//        if (StringUtils.isNotBlank(recodeInfo.getMissing2())) {
//            nMissing = 2;
//        }
//
//        int[] errorType = new int[1];
//        int[] errorLine = new int[1];
//        int[] errorPos = new int[1];
//        String[] warning = new String[1];
////The end of lines in the recode string cause a problem
//        hs = recodeInfo.getRecodeData();
////        hs = hs.replace("\n\r", "\n");
////        hs = hs.replace("\n", "\n\r");     
////        hs = hs + "\n\r";
//        if (tauArgus.DoRecode(index, hs, nMissing, recodeInfo.getMissing1(), recodeInfo.getMissing2(), errorType, errorLine, errorPos, warning)) {
//            currentRecodeCodeListFile = recodeInfo.getCodeList();
//            recoded = true;
//            truncLevels = 0;
//            logger.info("Recode: variable (" + name + ") has been recoded");
//        } else {
//            throw new ArgusException(tauArgus.GetErrorString(errorType[0]) + " " + warning[0] + " in recoding; line " + errorLine[0] + " pos " + errorPos[0]);
//        }
//    }
//    
//    public void applyRecodeTree(String fileName) throws ArgusException {
////Anco 1.6 try with resources     
////overigens wordt de reader niet gesloten ???????        
////        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));) {        
//      BufferedReader reader = null;  
//      try {reader = new BufferedReader(new FileReader(new File(fileName)));                
//            tauArgus.UndoRecode(index);
//            String regel = reader.readLine();
//            if (!StringUtils.equals(regel, "<TREERECODE>")) {
//                throw new ArgusException("First line does not start with \"<TREERECODE>\"");
//            }
//            while ((regel = reader.readLine()) != null) {
//                if (StringUtils.isNotBlank(regel)) {
//                    int codeIndex = TauArgusUtils.getCodeIndex(index, regel);
//                    if (codeIndex == -1) {
//                        throw new ArgusException("Code (" + regel + ") not found");
//                    } 
//                    tauArgus.SetVarCodeActive(index, codeIndex, false);
//                }
//            }
//            tauArgus.DoActiveRecode(index);
//        }
//        catch (Exception ex) {
//            
//            tauArgus.UndoRecode(index);
//            throw new ArgusException("Error in reading tree status in recode file " + fileName + ": " + ex.getMessage());
//        }
//    }
//    
//    public void recode(String fileName) throws ArgusException, FileNotFoundException, IOException {
//        if (hierarchical != HIER_NONE) {
//            applyRecodeTree(fileName);
//            logger.info("Variable (" + name + ") recoded.\nRecode file used:" + fileName);
//        } else {
//            RecodeInfo recodeInfo = readRecodeFile(fileName);
//            recode(recodeInfo);
//            currentRecodeFile = fileName;
//        }
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        final Variable variable = (Variable) obj;

        boolean equal = name.equals(variable.name)
                && type == variable.type
                && bPos == variable.bPos
                && varLen == variable.varLen;
//                && truncatable == variable.truncatable // only needed fot MuArgus

        if (variable.isCategorical()) {
            equal = equal
                    && totCode.equals(variable.totCode)
                    && Arrays.equals(missing, variable.missing)
                    && hasDistanceFunction == variable.hasDistanceFunction
                    && (!hasDistanceFunction || Arrays.equals(distanceFunction, variable.distanceFunction))
                    && codeListFile.equals(variable.codeListFile)
                    && hierarchical == variable.hierarchical
                    && (hierarchical != Variable.HIER_LEVELS || (hierLevelsSum == variable.hierLevelsSum && Arrays.equals(hierLevels, variable.hierLevels)))
                    && (hierarchical != Variable.HIER_FILE || (leadingString.equals(variable.leadingString) && hierFileName.equals(variable.hierFileName)));
        }

        if (variable.hasDecimals()) {
            equal = equal
                    && nDecimals == variable.nDecimals;
        }

        if (variable.type == Type.REQUEST) {
            equal = equal && Arrays.equals(requestCode, variable.requestCode);
        }
        
        return equal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + this.bPos;
        hash = 89 * hash + this.varLen;
        hash = 89 * hash + this.nDecimals;
        hash = 89 * hash + Arrays.deepHashCode(this.requestCode);
        hash = 89 * hash + (this.hasDistanceFunction ? 1 : 0);
        hash = 89 * hash + Arrays.hashCode(this.distanceFunction);
        hash = 89 * hash + this.hierarchical;
        hash = 89 * hash + Objects.hashCode(this.hierFileName);
        hash = 89 * hash + this.hierLevelsSum;
        hash = 89 * hash + Arrays.hashCode(this.hierLevels);
        hash = 89 * hash + Objects.hashCode(this.codeListFile);
        hash = 89 * hash + Objects.hashCode(this.leadingString);
        hash = 89 * hash + Arrays.deepHashCode(this.missing);
        hash = 89 * hash + Objects.hashCode(this.totCode);
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Variable variable = (Variable)super.clone(); 
        
        if (requestCode != null) {
            variable.requestCode = (String[])requestCode.clone();
        }
        if (distanceFunction != null) {
            variable.distanceFunction = (int[])distanceFunction.clone();
        }
        if (hierLevels != null) {
            variable.hierLevels = (int[])hierLevels.clone();
        }
        if (missing != null) {
            variable.missing = (String[])missing.clone();
        }
        variable.originalVariable = this;
        return variable;
    }
}
