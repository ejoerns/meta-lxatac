SUMMARY = "Utility tools"

inherit packagegroup

PACKAGES = ' \
            packagegroup-lxatac-tools-utils \
            '

RDEPENDS:${PN}-utils = "\
    git \
    mc \
    nano \
    ripgrep \
    tree \
    vim \
    "
