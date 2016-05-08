package com.comcast.technucleus.application.model;


import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class X1KnowIssues.
 */
public class X1KnowIssues {
	
    /** The identifiers. */
    private List<String[]> identifiers;

    /** The about. */
    private AboutX1KnownIssues about;

    /**
     * Gets the identifiers.
     *
     * @return the identifiers
     */
    public List<String[]> getIdentifiers ()
    {
        return identifiers;
    }

    /**
     * Sets the identifiers.
     *
     * @param identifiers the new identifiers
     */
    public void setIdentifiers (List<String[]> identifiers)
    {
        this.identifiers = identifiers;
    }

	/**
	 * Gets the about.
	 *
	 * @return the about
	 */
	public AboutX1KnownIssues getAbout() {
		return about;
	}

	/**
	 * Sets the about.
	 *
	 * @param about the new about
	 */
	public void setAbout(AboutX1KnownIssues about) {
		this.about = about;
	}
}

