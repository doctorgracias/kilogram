package hub.controller

import sttp.capabilities.zio.ZioStreams
import sttp.tapir._

case class ChatEndpoint() {
  val chat = endpoint
    .get
    .in("chat")
    .out(webSocketBody[String, CodecFormat.TextPlain, String, CodecFormat.TextPlain](ZioStreams))}
