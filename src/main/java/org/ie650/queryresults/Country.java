package org.ie650.queryresults;

import org.apache.jena.query.QuerySolution;

public class Country extends QueryResult{

    public static String NAME = "countryLabel";
    public static String CAPITAL = "capital";
    public static String CONTINENT = "continentLabel";
    public static String HEAD = "head";
    public static String DRIVING_SIDE = "drivingSideLabel";
    public static String AREA = "maxArea";
    public static String POPULATION = "maxPop";
    public static String HDI = "hdi";
    public Country(QuerySolution sol) {
        super(sol);
    }

    public long getArea() {
        return this.sol.getLiteral(AREA).getLong();
    }

    public double getHDI() {
        return this.sol.getLiteral(HDI).getDouble();
    }

    public long getPopulation() {
        return this.sol.getLiteral(POPULATION).getLong();
    }
}
