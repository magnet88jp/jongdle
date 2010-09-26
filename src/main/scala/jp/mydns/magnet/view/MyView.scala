package jp.mydns.magnet.view

import scala.xml._
import net.liftweb.http._

class MyView extends LiftView {
  override def dispatch = {
    case "view1" => doMyViewOne _
  }

  def doMyViewOne(): NodeSeq = {
    <lift:surround with="default" at="content">
       <div>初めてのビューディスパッチ</div>
    </lift:surround>
  }
}
