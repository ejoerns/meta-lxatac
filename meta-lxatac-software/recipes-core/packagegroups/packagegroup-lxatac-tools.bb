SUMMARY = "Utility tools"

inherit packagegroup

PACKAGES = ' \
            packagegroup-lxatac-tools-utils \
            packagegroup-lxatac-tools-utils-hw \
            '

RDEPENDS:${PN}-utils = "\
    git \
    mc \
    nano \
    ripgrep \
    tree \
    vim \
    "

RDEPENDS:${PN}-utils-hw = "\
    can-utils \
    ethtool \
    i2c-tools \
    imx-uuu \
    "
