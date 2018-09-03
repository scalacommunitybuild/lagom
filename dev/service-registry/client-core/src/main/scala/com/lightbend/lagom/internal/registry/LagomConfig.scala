/*
 * Copyright (C) 2016-2018 Lightbend Inc. <https://www.lightbend.com>
 */

package com.lightbend.lagom.internal.registry

import java.net.URI

import com.typesafe.config.Config

import scala.collection.immutable

object LagomConfig {
  def uris(config: Config): immutable.Seq[URI] = {
    // In dev mode, `play.server.http.address` is used for both HTTP and HTTPS.
    // Reading one value or the other gets the same result.
    val httpAddress = config.getString("play.server.http.address")
    List("http", "https").map { scheme =>
      val port = config.getString(s"play.server.$scheme.port")
      new URI(s"$scheme://$httpAddress:$port")
    }
  }
}
