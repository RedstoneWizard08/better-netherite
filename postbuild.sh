#!/bin/bash

set -e

mod_version="0.0.0"
archives_base_name="undefined"
minecraft_version="undefined"

source gradle.properties 2>/dev/null || true

[[ -d "build/libs" ]] && rm -f build/libs/*.jar

QUILT_JAR="quilt/build/libs/quilt-$mod_version.jar"
QUILT_SOURCES_JAR="quilt/build/libs/quilt-$mod_version-sources.jar"

FABRIC_JAR="fabric/build/libs/fabric-$mod_version.jar"
FABRIC_SOURCES_JAR="fabric/build/libs/fabric-$mod_version-sources.jar"

FORGE_JAR="forge/build/libs/$archives_base_name-v$mod_version+mc$minecraft_version-forge.jar"

[[ ! -d "build/libs" ]] && mkdir -p "build/libs"

[[ -f "$QUILT_JAR" ]] && cp -f "$QUILT_JAR" \
    "build/libs/$archives_base_name-v$mod_version+mc$minecraft_version-quilt.jar"

[[ -f "$QUILT_SOURCES_JAR" ]] && cp -f "$QUILT_SOURCES_JAR" \
    "build/libs/$archives_base_name-v$mod_version+mc$minecraft_version-quilt-sources.jar"

[[ -f "$FABRIC_JAR" ]] && cp -f "$FABRIC_JAR" \
    "build/libs/$archives_base_name-v$mod_version+mc$minecraft_version-fabric.jar"

[[ -f "$FABRIC_SOURCES_JAR" ]] && cp -f "$FABRIC_SOURCES_JAR" \
    "build/libs/$archives_base_name-v$mod_version+mc$minecraft_version-fabric-sources.jar"

[[ -f "$FORGE_JAR" ]] && cp -f "$FORGE_JAR" \
    "build/libs/$archives_base_name-v$mod_version+mc$minecraft_version-forge.jar"
