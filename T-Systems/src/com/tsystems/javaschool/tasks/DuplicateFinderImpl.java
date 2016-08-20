package com.tsystems.javaschool.tasks;

/*this class read file, then it adds unique lines and their quantity to another file
 * if there was no output file, it creates new, then adds same info in it
 * 
 * in case of success "true" is printed, otherwise "false" 
*/

import java.io.*;
import java.util.TreeMap;

public class DuplicateFinderImpl implements DuplicateFinder {
    
    private TreeMap<String, Integer> map;
    
    public DuplicateFinderImpl() {
	map = new TreeMap<>();
    }

    public static void main(String[] args) {
	DuplicateFinder d = new DuplicateFinderImpl();
	System.out.println(d.process(new File("a.txt"), new File("b.txt")));

    }

    @Override
    public boolean process(File sourceFile, File targetFile) {
	try {
	    uniqueLineTreeMap(sourceFile);
	    treeMapToFile(targetFile);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    
    //this method fills TreeMap,with unique lines as keys for map
    //and saves quantity of their appearance as values
    //each time, map is cleared, so you may use it on another file
    
    private void uniqueLineTreeMap(File sourceFile) throws IOException {
	map.clear();
	String line;
	try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
	    while ((line = br.readLine()) != null) {
		// we may trim line if we need to
		if (!map.containsKey(line)) {
		    map.put(line, 1);
		} else {
		    map.put(line, map.get(line) + 1);
		}
	    }
	    br.close();
	} catch (IOException exc) {
	    throw exc;
	}

    }

    //this method write data from TreeMap to a targetFile
    //if there is no targetFile it creates it
    private void treeMapToFile(File targetFile) throws IOException {
	String line;
	File tf;
	// check if file exists, if not, creates new
	// In both cases it associates it with File tr, and then works with tr
	if (!targetFile.exists()) {
	    tf = new File(targetFile.getPath());
	} else {
	    tf = targetFile;
	}
	// adds unique lines and their quantity
	try (FileWriter fw = new FileWriter(tf, true)) {
	    for (String key : map.keySet()) {
		line = key + " [" + map.get(key) + "]" + System.lineSeparator();
		fw.write(line);
		fw.flush();
	    }
	} catch (IOException exc) {
	    throw exc;
	}

    }

}
