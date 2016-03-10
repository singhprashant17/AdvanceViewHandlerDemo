# AdvanceViewSwitcherDemo

An example implemention of AdvanceViewSwitcher.

This library allows adding different views, identified by their unigus tags, and easy management between switching them.
There is <b>NO LIMIT</b> in number of views that can be added/handled.

<br />
Works seamlessly with both Activity and Fragment.<br />
The demo project has implementation with both Activity and Fragment.

<br />
NOTEABLE METHODS : 

1. <b>public void addMainView()</b> : <br />
   adds the view supplied to the constructor with TAG_MAIN_VIEW

2. <b>public void addViewWithTag(View view, int viewTag)</b> : <br />
   adding the view with specified tag. if tag is already present, returns

3. <b>public void displayViewWithTag(int viewTag)</b> : <br />
   displays view associated with the provided viewTag. If not found, returns

4. <b>public void addAndDisplayWithTag(View view, int viewTag)</b> : <br />
   adds and display the view as soon as after adding


