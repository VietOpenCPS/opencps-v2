
package org.mobilink.api.model;

public class Sample {

	private String foo;
	private Integer bar;
	private Boolean baz;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public Sample withFoo(String foo) {
		this.foo = foo;
		return this;
	}

	public Integer getBar() {
		return bar;
	}

	public void setBar(Integer bar) {
		this.bar = bar;
	}

	public Sample withBar(Integer bar) {
		this.bar = bar;
		return this;
	}

	public Boolean getBaz() {
		return baz;
	}

	public void setBaz(Boolean baz) {
		this.baz = baz;
	}

	public Sample withBaz(Boolean baz) {
		this.baz = baz;
		return this;
	}

}