package jp.mydns.magnet {
package snippet {

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util.Helpers
//import _root_.net.liftweb.util.{Box, Full}
import Helpers._
import _root_.net.liftweb.http.SHtml._
import _root_.net.liftweb.http.js.JE._
import _root_.net.liftweb.http.js.JsCmds._

//import _root_.jp.mydns.magnet.model.Table

class HelloWorld {
  def howdy(in: NodeSeq): NodeSeq =
    Helpers.bind("b", in, "time" -> (new _root_.java.util.Date).toString)

  def greating(html: NodeSeq) : NodeSeq = {
    bind("greating", html,
      "button" -> ajaxButton( Text( "押して"), {
        () => {
          println("Ajaxで呼び出されました。")
//          val username = User.currentUser.dmap( "Guest" ){ _.shortName }
          SetHtml("greating-div",
//            Text ("こんにちは！%sさん。".format(username))
            Text ("こんにちは！ajaxさん。")
          )
        }
      })
    )
  }
}

}
}
