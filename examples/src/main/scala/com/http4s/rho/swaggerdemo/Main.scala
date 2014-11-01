package com.http4s.rho.swaggerdemo

import org.http4s.Header
import org.http4s.Header.{ `Access-Control-Allow-Origin` }
import org.http4s.server.blaze.BlazeServer
import JsonWritable.AutoSerializable




object Main extends App {
  val builder = BlazeServer.newBuilder
  val service = MyService.andThen { t =>
    for (r <- t) yield r.putHeaders(Header(`Access-Control-Allow-Origin`.name.toString, "*"))
  }
  builder.mountService(service)
    .withPort(8080)
    .build
    .run()
}
