package bootstrap.liftweb

import _root_.net.liftweb.common._
import _root_.net.liftweb.util._
import _root_.net.liftweb.http._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._

/**
  * A class that's instantiated early and run.  It allows the application
  * to modify lift's environment
  */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("jp.mydns.magnet")

    // Build SiteMap
    val entries = Menu(Loc("Home", List("index"), "Home")) ::
      Menu(Loc("Table", List("table"), "Table")) ::
      Menu(Loc("MyView1", List("MyView","view1"), "my view1")) ::
      Menu(Loc("Save", List("save"), "Save")) :: Nil
    LiftRules.setSiteMap(SiteMap(entries:_*))

  }
}

