# AdvanceViewSwitcherDemo

An example implemention of AdvanceViewSwitcher.

This library allows adding different views, identified by their unigus tags, and easy management between switching them.
There is NO LIMIT in number of views that can be handled.


NOTEABLE METHODS : 

1. public void addMainView()
   adds the view supplied to the constructor with TAG_MAIN_VIEW

2. public void addViewWithTag(View view, int viewTag)
   adding the view with specified tag. if tag is already present, returns

3. public void displayViewWithTag(int viewTag)
   displays view associated with the provided viewTag. If not found, returns

4. public void addAndDisplayWithTag(View view, int viewTag)
   adds and display the view as soon as after adding
