SUMMARY = "barebox bootloader tools"
HOMEPAGE = "https://barebox.org/"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=f5125d13e000b9ca1f0d3364286c4192"

DEPENDS = "libusb1 bison-native flex-native"

SRC_URI = "https://barebox.org/download/barebox-${PV}.tar.bz2"
SRC_URI[sha256sum] = "6a584d1fbb5d8ea6ce0c73d3a6c83ff531f488c64cbd6354d32fd420159539ed"

S = "${WORKDIR}/barebox-${PV}"
B = "${WORKDIR}/build"

inherit pkgconfig

EXTRA_OEMAKE = " \
  ARCH=sandbox \
  CROSS_COMPILE=${TARGET_PREFIX} -C ${S} O=${B} \
  CROSS_PKG_CONFIG=pkg-config \
  CC='${CC}' \
  LD='${LD}' \
  "

do_compile:class-target () {
    export userccflags="${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}"
    export userldflags="${TARGET_LDFLAGS}${TOOLCHAIN_OPTIONS}"
    oe_runmake targettools_defconfig
    oe_runmake scripts
}

do_compile:class-native () {
    oe_runmake hosttools_defconfig
    oe_runmake scripts
}

BAREBOX_TOOLS = " \
         bareboxenv \
         bareboxcrc32 \
         kernel-install \
         bareboximd \
         omap3-usb-loader \
         omap4_usbboot \
         imx/imx-usb-loader \
         "

BAREBOX_TOOLS_SUFFIX = ""
BAREBOX_TOOLS_SUFFIX:class-target = "-target"

do_install () {
	install -d ${D}${bindir}

	for tool in ${BAREBOX_TOOLS}; do
		install -m 0755 scripts/${tool}${BAREBOX_TOOLS_SUFFIX} ${D}${bindir}/${tool##*/}
	done
}

BBCLASSEXTEND = "native nativesdk"
