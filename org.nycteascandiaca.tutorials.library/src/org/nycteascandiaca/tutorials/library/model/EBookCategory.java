package org.nycteascandiaca.tutorials.library.model;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum EBookCategory
{
	CLASSICS,
	BIOGRAPHY,
	EDUCATION,
	HISTORY,
	SCENCE,
	COMPUTER_SCIENCE
}
