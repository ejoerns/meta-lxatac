
PACKAGECONFIG:append = " \
  cgroupv2 \
  openssl \
  pstore \
  repart \
  zstd \
"

PACKAGECONFIG:remove = "ima"
